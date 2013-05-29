(defproject tawny-go "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [uk.org.russet/tawny-owl "0.12-SNAPSHOT"]
                 [net.sourceforge.owlapi/owlapi-api "3.4.4"]
                 ]

  :main tawny.obo.go.core
  :jvm-opts ["-Xmx500m"]

  )
