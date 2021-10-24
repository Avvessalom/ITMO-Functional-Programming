(defproject itmo-functional-programming "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :profiles {
             :lab_1 {
                     :repl-options {
                                    :init-ns itmo-functional-programming.lab1.main
                                    :package itmo-functional-programming.lab1
                                    }
                     :main itmo-functional-programming.lab1.main
                     }
             })
