(ns itmo-functional-programming.lab2.hash-set-property-based-tests
  ^{:doc    "Lab 2 Property Base Tests
          Properties for set
          1. Intersection is associative;                 V
          2. Intersection is commutative;                 V
          3. The empty set is the zero for intersection;  V
          4. Union is associative;                        V
          5. Union is commutative;                        V
          6. The empty set is the identity for union;     V
          7. Intersection distributes over union;         V
          8. Union distributes over intersection;         V
          9. De Morgan’s law;                             X
          10. Intersection is idempotent;                 V
          11. Union is idempotent;                        V"
    :author "Lazurin Eugene"}
  (:require [clojure.test :refer :all]
            [itmo-functional-programming.lab2.hash-set :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(def Empty (my-hash-set))

(def commutativity-property
  "A U B = B U A
   A ⋂ B = B ⋂ A"
  (prop/for-all [A (gen/fmap my-hash-set (gen/vector gen/nat))
                 B (gen/fmap my-hash-set (gen/vector gen/nat))]
                (and
                  (= (union A B) (union B A))
                  (= (intersection A B) (intersection B A)))))

(def associativity-property
  "A U (B U C) = (A U B) U C
   A ⋂ (B ⋂ C) = (A ⋂ B) ⋂ C
   The binary union operation is defined everywhere and associative. Hence, it is a monoid."
  (prop/for-all [A (gen/fmap my-hash-set (gen/vector gen/nat))
                 B (gen/fmap my-hash-set (gen/vector gen/nat))
                 C (gen/fmap my-hash-set (gen/vector gen/nat))]
                (and (= (union A (union B C)) (union C (union A B)))
                     (= (intersection A (intersection B C)) (intersection C (intersection A B))))))

(def idempotent-property
  "A ∪ A = A
   A ⋂ A = A"
  (prop/for-all [A (gen/fmap my-hash-set (gen/vector gen/nat))]
                (and
                  (= A (union A A))
                  (= A (intersection A A)))))

(def empty-property
  "A ⋂ ∅ = ∅ = ∅ ⋂ A
   A U ∅ = A = ∅ U A
   monoid property: ε is a neutral element concerning union.
   That is, the following is performed for it: ∀x∈G:εUx=xUε=x"
  (prop/for-all [A (gen/fmap my-hash-set (gen/vector gen/nat))]
                (and (= (intersection A Empty) (intersection Empty A))
                     (= (intersection A Empty) Empty)
                     (= (union A Empty) A)
                     (= A (union Empty A))
                     (= (union A Empty) (union Empty A)))))

(def distribute-property
  "A ⋂ (B U C) = (A ⋂ B) U (A ⋂ C)
   A U (B ⋂ C) = (A U B) ⋂ (A U C)"
  (prop/for-all [A (gen/fmap my-hash-set (gen/vector gen/nat))
                 B (gen/fmap my-hash-set (gen/vector gen/nat))
                 C (gen/fmap my-hash-set (gen/vector gen/nat))]
                (and
                  (= (intersection A (union B C)) (union (intersection A B) (intersection A C)))
                  (= (union A (intersection B C)) (intersection (union A B) (union A C))))))

(tc/quick-check 100 commutativity-property)
(tc/quick-check 100 associativity-property)
(tc/quick-check 100 idempotent-property)
(tc/quick-check 100 empty-property)
(tc/quick-check 100 distribute-property)
