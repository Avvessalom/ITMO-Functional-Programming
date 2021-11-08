(ns itmo-functional-programming.lab2.hash-set-unit-tests
  (:require [clojure.test :refer :all]
            [itmo-functional-programming.lab2.hash-set :refer :all]
            [itmo-functional-programming.lab2.hash-set-property-based-tests :refer :all]))

(def full-set (my-hash-set 1 1 1 2 2 3 3 4 4 5 5 5 6 6 7 8 9 9 9 9 9 9 9))
(def etalon (hash-set 1 1 1 2 2 3 3 4 4 5 5 5 6 6 7 8 9 9 9 9 9 9 9))
(def empty-set (my-hash-set))
(def a (my-hash-set 1 1 2 3))
(def b (my-hash-set 2 2 3 4))

(deftest my-set-test
  (testing (is (= full-set (my-hash-set 1 2 3 4 5 6 7 8 9)))))

(deftest set-first-test
  (testing (is (= (first full-set) (first etalon)))))

(deftest full-set-count-test
  (testing (is (= 0 (count empty-set)))))

(deftest full-set-count-test
  (testing (is (= 9 (count full-set)))))

(deftest set-next-test
  (testing (is (= (next full-set) (next etalon)))))

(deftest set-more-test
  (testing (is (= (rest full-set) (rest etalon)))))

(deftest set-get-test
  (testing (is (= (get full-set 1) 1))))

(deftest set-contains-test
  (testing
    (is (contains? full-set 1))
    (is (not (contains? full-set 11)))))

(deftest set-disjoin-test
  (testing (is (= (disj full-set 1) (disj etalon 1)))))

(deftest set-empty-test
  (testing (is (= (empty full-set) empty-set))))

(deftest set-seq-test
  (testing (is (= (seq full-set) (seq etalon)))))

(deftest set-cons-test
  (testing (is (= (.cons full-set 1) (conj etalon 1)))))

(deftest union-operation-test
  (testing
    (is (= (union a b) (my-hash-set 1 2 3 4)))
    (is (not= (union a b) (my-hash-set 1 2 3 5)))))

(deftest intersection-operation-test
  (testing
    (is (= (intersection a b) (my-hash-set 2 3)))
    (is (not= (intersection a b) (my-hash-set 1 2 3 4)))))