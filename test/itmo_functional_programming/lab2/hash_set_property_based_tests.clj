(ns itmo-functional-programming.lab2.hash-set-property-based-tests
  (:require [clojure.test :refer :all]
            [itmo-functional-programming.lab2.hash-set :refer :all]))

; Properties
; 1. Intersection is associative;                 V
; 2. Intersection is commutative;                 V
; 3. The empty set is the zero for intersection;  V
; 4. Union is associative;                        V
; 5. Union is commutative;                        V
; 6. The empty set is the identity for union;     V
; 7. Intersection distributes over union;         V
; 8. Union distributes over intersection;         V
; 9. De Morgan’s law;                             X
; 10. Intersection is idempotent;                 V
; 11. Union is idempotent;                        V

(def A (hash-set 1 1 1 2 2 2 3 3 5))
(def B (hash-set 1 2 3 4 4 4 12 12 12 8 8 52))
(def C (hash-set 11 1 16 22 2 22 34 3 555))
(def Empty (hash-set))

(defn intersection
  ([set1] set1)
  ([set1 set2]
   (if (< (count set2) (count set1))
     (recur set2 set1)
     (reduce (fn [result item]
               (if (contains? set2 item)
                 result
                 (disj result item)))
             set1 set1))))

(defn union
  ([] #{})
  ([set1] set1)
  ([set1 set2]
   (if (< (count set1) (count set2))
     (reduce conj set2 set1)
     (reduce conj set1 set2))))

(deftest commutativity-property-test
  (testing "A U B = B U A
            A ⋂ B = B ⋂ A"
    (is (= (union A B) (union B A)))
    (is (= (intersection A B) (intersection B A)))))

(deftest associativity-property-test
  (testing "A U (B U C) = (A U B) U C
            A ⋂ (B ⋂ C) = (A ⋂ B) ⋂ C"
    (is (= (union A (union B C)) (union C (union A B))))
    (is (= (intersection A (intersection B C)) (intersection C (intersection A B))))))

(deftest idempotent-property-test
  (testing "A ∪ A = A
            A ⋂ A = A"
    (is (= A (union A A)))
    (is (= A (intersection A A)))))

(deftest empty-property-test
  (testing "A ⋂ ∅ = ∅ = ∅ ⋂ A
            A U ∅ = A = ∅ U A")
  (is (= (intersection A Empty) (intersection Empty A)))
  (is (= (intersection A Empty) Empty))
  (is (= (union A Empty) A))
  (is (= (union A Empty) (union Empty A))))

(deftest distribute-property-test
  (testing "A ⋂ (B U C) = (A ⋂ B) U (A ⋂ C)
            A U (B ⋂ C) = (A U B) ⋂ (A U C)"
    (is (= (intersection A (union B C)) (union (intersection A B) (intersection A C))))
    (is (= (union A (intersection B C)) (intersection (union A B) (union A C))))))
