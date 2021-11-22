(ns itmo-functional-programming.lab2.hash-set-unit-tests
  (:require [clojure.test :refer :all]
            [itmo-functional-programming.lab2.hash-set :refer :all]
            [itmo-functional-programming.lab2.hash-set-property-based-tests :refer :all]))

(deftest my-set-test
  (let [set-for-test (my-hash-set 1 1 2)
        fake-set (my-hash-set 2 2)]
    (is (= set-for-test (my-hash-set 1 1 2)))
    (is (not= set-for-test fake-set))))

(deftest set-first-test
  (let [set-for-test (my-hash-set 1 1 2)
        true-set (hash-set 1 1 2)
        fake-set (my-hash-set 2 2)]
    (is (= (first set-for-test) (first true-set)))
    (is (not= (first set-for-test) (first fake-set)))))

(deftest set-count-test
  (let [empty-set (my-hash-set)
        set-for-test (my-hash-set 1 1 2)]
    (is (= 0 (count empty-set)))
    (is (not= 1 (count empty-set)))
    (is (= 2 (count set-for-test)))
    (is (not= 0 (count set-for-test)))))

(deftest set-next-test
  (let [set-for-test (my-hash-set 1 1 2)
        true-set (hash-set 1 1 2)
        fake-set (my-hash-set 1 1)]
    (is (= (next set-for-test) (next true-set)))
    (is (not= (next set-for-test) (next fake-set)))))

(deftest set-more-test
  (let [set-for-test (my-hash-set 1 1 2)
        true-set (hash-set 1 1 2)
        fake-set (my-hash-set 2 2)]
    (is (= (rest set-for-test) (rest true-set)))
    (is (not= (rest set-for-test) (rest fake-set)))))

(deftest set-get-test
  (let [set-for-test (my-hash-set 1 1 2)]
    (is (= (get set-for-test 1) 1))
    (is (not= (get set-for-test 1) 2))
    (is (not= (get set-for-test 3) 3))))

(deftest set-contains-test
  (let [set-for-test (my-hash-set 1 1 2)]
    (is (contains? set-for-test 1))
    (is (not (contains? set-for-test 11)))))

(deftest set-disjoin-test
  (let [set-for-test (my-hash-set 1 1 2)
        true-set (hash-set 1 1 2)]
    (is (= (disj set-for-test 1) (disj true-set 1)))))

(deftest set-empty-test
  (let [set-for-test (my-hash-set 1 1 2)
        empty-set (my-hash-set)]
    (is (= (empty set-for-test) empty-set))
    (is (not= (empty set-for-test) set-for-test))))

(deftest set-seq-test
  (let [set-for-test (my-hash-set 1 1 2)
        true-set (hash-set 1 1 2)]
    (is (= (seq set-for-test) (seq true-set)))
    (is (= (seq set-for-test) (list 1 2)))))

(deftest set-cons-test
  (let [set-for-test (my-hash-set 1 1 2)
        true-set (hash-set 1 1 2)]
    (is (= (.cons set-for-test 1) (conj true-set 1)))
    (is (= (.cons set-for-test 3) (conj true-set 3)))
    (is (not= (.cons set-for-test 1) (conj set-for-test 4)))))

(deftest union-operation-test
  (let [a (my-hash-set 1 1 2 3)
        b (my-hash-set 2 2 3 4)]
    (is (= (union a b) (my-hash-set 1 2 3 4)))
    (is (not= (union a b) (my-hash-set 1 2 3 5)))))

(deftest intersection-operation-test
  (let [a (my-hash-set 1 1 2 3)
        b (my-hash-set 2 2 3 4)]
    (is (= (intersection a b) (my-hash-set 2 3)))
    (is (not= (intersection a b) (my-hash-set 1 2 3 4)))))
