(defproject itmo-functional-programming "0.1.0-SNAPSHOT"
  :description "Functional programming labs"
  :url "https://github.com/Avvessalom/itmo-functional-programming"
  :license {:name "MIT"
            :url "https://github.com/Avvessalom/itmo-functional-programming/blob/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [nrepl/lein-nrepl "0.3.2"]
                 [org.clojure/test.check "1.1.0"]]
  :profiles {
             :lab_1 {
                     :repl-options {
                                    :init-ns itmo-functional-programming.lab1.main
                                    :package itmo-functional-programming.lab1
                                    }
                     :main itmo-functional-programming.lab1.main
                     }
             :lab_2 {
                     :repl-options {
                                    :init-ns itmo-functional-programming.lab2.main
                                    :package itmo-functional-programming.lab2
                                    }
                     :main itmo-functional-programming.lab2.main
                     }
             })
