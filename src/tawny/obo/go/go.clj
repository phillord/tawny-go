

(ns tawny.obo.go.go
  (:refer-clojure :only [partial])
  (:require [tawny.owl]
            [tawny.read]
            [tawny.memorise])
  (:import (java.io File)
           (org.semanticweb.owlapi.model IRI OWLNamedObject)))


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
  (partial tawny.read/iri-starts-with-filter 
           "http://purl.obolibrary.org/obo/GO")
  :transform
  tawny.read/exception-nil-label-transform
  )


