(ns itmo-functional-programming.lab1.task-24
  "Task 24 from Project Euler:
    - A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4.
    - If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:
    - 012   021   102   120   201   210
    - What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
  "
  )

(def digits '(0 1 2 3 4 5 6 7 8 9))

(defn next-lex [substr digits]
  (let [lex (apply str substr digits)]
    (lazy-cat
      [lex]
      (loop [s (vec lex)
             d (sorted-set)]
        (let [i (- (int (peek s)) 48)
              d (conj d i)]
          (if (== (last d) i)
            (recur (pop s) d)
            (let [z (first (drop-while #(<= % i) d))]
              (next-lex (apply str (conj (pop s) (char (+ z 48)))) (disj d z)))))))))

(defn lex-perm-stupid [num]
  (first (drop (dec num) (next-lex "" digits))))

(defn lex-perm-stupid-with-macro [num]
  (->> (next-lex "" digits)
       (drop (dec num))
       (first)))

(defn factorial [n]
  (reduce *' (range 1 (inc n))))

(defn lex-perm-module [n s]
  (if (= (count s) 1)
     s
     (let [q (quot n (factorial (dec (count s))))
            r (rem n (factorial (dec (count s))))]
        (cons (nth s q) (lex-perm-module r (vec (concat (take q s) (drop (inc q) s))))))))

(defn lex-perm-module-answer [n]
  (apply str (lex-perm-module (dec n) digits)))

(defn task-24-report []
  (do
    (printf "Task 24 solutions:
                * stupid: %s
                * stupid with macro: %s
                * module: %s \n" (time (lex-perm-stupid 1000000)) (time (lex-perm-stupid-with-macro 1000000)) (time (lex-perm-module-answer 1000000)))))
