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

(def Empty (my-hash-set))

(defn run-test [test-fn times] (reduce #(and %1 %2) (repeatedly times test-fn)))
(defn generate-vec [size] (repeatedly size #(rand-int 10E+6)))

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

(defn commutativity-property []
  ; A U B = B U A
  ; A ⋂ B = B ⋂ A
  (let [A (apply my-hash-set (generate-vec 100))
        B (apply my-hash-set (generate-vec 100))]
    (and (= (union A B) (union B A))
         (= (intersection A B) (intersection B A)))))

(defn associativity-property []
  ; A U (B U C) = (A U B) U C
  ; A ⋂ (B ⋂ C) = (A ⋂ B) ⋂ C
  (let [A (apply my-hash-set (generate-vec 100))
        B (apply my-hash-set (generate-vec 100))
        C (apply my-hash-set (generate-vec 100))]
    (and (= (union A (union B C)) (union C (union A B)))
         (= (intersection A (intersection B C)) (intersection C (intersection A B))))))

(defn idempotent-property []
  ; A ∪ A = A
  ; A ⋂ A = A
  (let [A (apply my-hash-set (generate-vec 100))]
    (and (= A (union A A))
         (= A (intersection A A)))))

(defn empty-property []
  ; A ⋂ ∅ = ∅ = ∅ ⋂ A
  ; A U ∅ = A = ∅ U A
  (let [A (apply my-hash-set (generate-vec 100))]
    (and (= (intersection A Empty) (intersection Empty A))
         (= (intersection A Empty) Empty)
         (= (union A Empty) A)
         (= (union A Empty) (union Empty A)))))

(defn distribute-property []
  ; A ⋂ (B U C) = (A ⋂ B) U (A ⋂ C)
  ; A U (B ⋂ C) = (A U B) ⋂ (A U C)
  (let [A (apply my-hash-set (generate-vec 100))
        B (apply my-hash-set (generate-vec 100))
        C (apply my-hash-set (generate-vec 100))]
    (and (= (intersection A (union B C)) (union (intersection A B) (intersection A C)))
         (= (union A (intersection B C)) (intersection (union A B) (union A C))))))

(deftest commutativity-property-test
  (is (run-test commutativity-property 100)))

(deftest associativity-property-test
  (is (run-test associativity-property 100)))

(deftest idempotent-property-test
  (is (run-test idempotent-property 100)))

(deftest empty-property-test
  (is (run-test empty-property 100)))

(deftest distribute-property-test
  (is (run-test distribute-property 100)))