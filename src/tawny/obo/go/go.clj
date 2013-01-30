

(ns tawny.obo.go.go
  (:refer-clojure :only [doall defn str println fn and filter instance? first filter])
  (:require [tawny.owl]
            [tawny.read]
            [tawny.memorise])
  (:import (java.io File)
           (org.semanticweb.owlapi.model IRI OWLNamedObject)))




(defn filter-for-go [e]
  (try
    ;;(println "Filter:" e)
    (and (instance? OWLNamedObject e)
         (.startsWith
          (.toString (.getIRI e))
          "http://purl.obolibrary.org/obo/GO"
          ))
    (catch Throwable t
      (println "Problems in filter!: " t)
      (throw t))))

;; fix the space problem
(defn transform-for-go [e]
  (try
    (clojure.string/replace
     ;; with luck these will always be literals, so we can do this
     ;; although not true in general
     (.getLiteral 
      ;; get the value of the annotation
      (.getValue
       (first
        ;; filter for annotations which are labels
        ;; is lazy, so doesn't eval all
        (doall
         (filter
          #(do
             ;;(println "Loading:" %)
             (.isLabel (.getProperty %)))
          ;; get the annotations the big assumption here is that this makes
          ;; sense. If the entities in question are AnnotationProperties, I am
          ;; not sure that it is going to work.
          (.getAnnotations e
                           (tawny.owl/get-current-ontology)))))))
     #"[ /]" "_"
     )
    (catch Throwable t
      (println "Problems!: " e)
      (throw t))
    ))


(tawny.read/defread go
  ;; something that the OWL API can interpret. This includes a stream, so
  ;; it's totally generic.
  :location (IRI/create (clojure.java.io/resource "go.owl"))
  ;; the prefix that you want to use in this case
  :prefix "go"
  ;; normally only things from this IRI will be imported
  :iri "http://purl.obolibrary.org/obo/go.owl"
  :viri "http://purl.obolibrary.org/obo/obi/2012-12-16/go.owl"
  ;; but OBO ontologies are wierd, so pass in a filter function
  :filter
  filter-for-go
  :transform
  transform-for-go
  )
