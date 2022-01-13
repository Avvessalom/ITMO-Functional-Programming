(ns itmo-functional-programming.lab3.lagrange)

(defn lagrange-polint [points]
  (let [xs (mapv first points)
        ys (mapv second points)
        xs-denom (mapv (fn [x y]
                         (/ y
                            (reduce (fn [r xn]
                                      (if (== x xn)
                                        r
                                        (* r (- x xn))))
                                    1
                                    xs)))
                       xs
                       ys)
        numerator-f (fn [i x]
                      (reduce-kv (fn [r j xj]
                                   (if (== i j)
                                     r
                                     (* r (- x xj))))
                                 1
                                 xs))]
    (fn [x]
      (reduce +
              (map-indexed (fn [i dn] (* (numerator-f i x) dn))
                           xs-denom)))))
(mapv (lagrange-polint [[1 1] [2 8] [3 27] [6 216]]) [2.1 5])
