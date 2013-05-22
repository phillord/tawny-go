(defproject tawny-go "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [uk.org.russet/tawny-owl "LATEST"]
                 [net.sourceforge.owlapi/owlapi-api "3.4.4"]
                 ]

  :main tawny.obo.go.core
  :jvm-opts ["-Xmx500m"]

  )
