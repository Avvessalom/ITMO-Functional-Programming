(ns itmo-functional-programming.lab1.task-24-test
  (:require [clojure.test :refer :all]
            [itmo-functional-programming.lab1.task-24 :refer :all]))

(deftest twenty-fourth-task-stupid-test
  (testing "24-th task, stupid."
    (is (= (lex-perm-stupid 10001) "0139846725"))
    (is (= (lex-perm-stupid 1) "0123456789"))
    (is (= (lex-perm-stupid 10) "0123457896"))
    (is (= (lex-perm-stupid 100) "0123495786"))))

(deftest twenty-fourth-task-stupid-with-macro-test
  (testing "24-th task, stupid with macro."
    (is (= (lex-perm-stupid-with-macro 10001) "0139846725"))
    (is (= (lex-perm-stupid-with-macro 1) "0123456789"))
    (is (= (lex-perm-stupid-with-macro 10) "0123457896"))
    (is (= (lex-perm-stupid-with-macro 100) "0123495786"))))

(deftest twenty-fourth-task-module-test
  (testing "24-th task, module."
    (is (= (lex-perm-module-answer 10001) "0139846725"))
    (is (= (lex-perm-module-answer 1) "0123456789"))
    (is (= (lex-perm-module-answer 10) "0123457896"))
    (is (= (lex-perm-module-answer 100) "0123495786"))))
