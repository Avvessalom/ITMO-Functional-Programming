(ns itmo-functional-programming.lab3.newton)

;(defn newtons-method-r [f f' tolerance x n]
;  (if (<= (abs (f x)) tolerance)
;    n
;    (recur f f' tolerance (- x (/ (f x) (f' x))) (inc n))))

(defn factorial [n]
  (reduce *' (range 1 (inc n))))

(defn coef
  [delta y h i]
  (/ (* (Math/pow delta i) y) (* (factorial i) (Math/pow h i))))

(defn newtons-method
  [points]
  (let [n (count points)]

    (println "KEKL" n)))

(defn fu []
  ((loop [sum 0
          cnt 10]
     (let [c (coef 1 2 1 cnt)]
       (if (= cnt 0)
         (println sum)
         (recur (+ c sum) (dec cnt)))))

   (doseq [[x y] (map list [1 2 3] [1 2 3])]
     (prn (* x y)))

   (loop [i 10
          sum 0]
     (if (= i 0)
       sum
       (recur (+ (coef 1 2 3 4) sum) (dec i))))))

(defn kek [x y z]
  (/ y
     (reduce (fn [r xn]
               (if (== x xn)
                 r
                 (* r (- x xn))))
             1
             z)))
