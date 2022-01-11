(ns itmo-functional-programming.lab2.hash-map-unit-tests
  (:require [clojure.test :refer :all]
            [itmo-functional-programming.lab2.hash-map :refer :all]))


(deftest my-map-test
  (let [map-for-test (my-hash-map {1 2 3 4})
        fake-map (my-hash-map {5 6 7 8})]
    (is (= map-for-test (my-hash-map {1 2 3 4})))
    (is (not= map-for-test fake-map))))

(deftest map-first-test
  (let [map-for-test (my-hash-map {1 2 3 4})
        true-map (hash-map 1 2 3 4)
        fake-map (my-hash-map {5 6 7 8})]
    (is (= (first map-for-test) (first true-map)))
    (is (not= (first map-for-test) (first fake-map)))))

(deftest map-count-test
  (let [empty-map (my-hash-map)
        map-for-test (my-hash-map {1 2 3 4})]
    (is (= 0 (count empty-map)))
    (is (not= 1 (count empty-map)))
    (is (= 2 (count map-for-test)))
    (is (not= 0 (count map-for-test)))))

(deftest map-next-test
  (let [map-for-test (my-hash-map {1 2 3 4})
        true-map (hash-map 1 2 3 4)
        fake-map (my-hash-map {5 6 7 8})]
    (is (= (next map-for-test) (next true-map)))
    (is (not= (next map-for-test) (next fake-map)))))

(deftest map-empty-test
  (let [map-for-test (my-hash-map {1 2 3 4})
        empty-map (my-hash-map)]
    (is (= (empty map-for-test) empty-map))
    (is (not= (empty map-for-test) map-for-test))))

(deftest map-seq-test
  (let [map-for-test (my-hash-map {1 2 3 4})]
    (is (= (seq map-for-test) (list 1 2 3 4)))))
