(ns itmo-functional-programming.lab2.hash-map
  (:import (clojure.lang IPersistentMap IPersistentCollection IMapEntry Seqable ILookup Associative)
           (java.io Writer)))

(defrecord MyHashMapEntry [key val]
  IMapEntry
  (getKey [_] key)
  ;(hashCode [_] (println "not implemented"))
  (getValue [_] val)
  Object
  (toString [_] (str ("$key, $val"))))

(declare ->MyHashMap)
(deftype MyHashMap [src]
  IPersistentMap
  (without [_ key] (dissoc key (list src)))

  Seqable
  (seq [_] (mapcat list (map #(.getKey %) (seq src)) (map #(.getValue %) (seq src))))

  ILookup
  (valAt [_ k]
    (if (not= (.indexOf (vec (map #(.getKey %) (seq src))) k) -1)
      (get (vec (map #(.getValue %) (seq src))) (.indexOf (vec (map #(.getKey %) (seq src))) k))
      false))

  Associative
  (containsKey [_ k] (contains? (vec src) k))

  Object
  (toString [_] (apply str (vector src)))

  IPersistentCollection
  (empty [_] (->MyHashMap nil))
  (count [_] (count src)))

(defmethod print-method MyHashMap [m, ^Writer w]
  (.write w (str "{" (mapcat list (map #(.getKey %) (seq m)) (map #(.getValue %) (seq m))) "}")))

(defn my-hash-map
  ([] (->MyHashMap nil))
  ([keyvals]
   (->MyHashMap (map #(->MyHashMapEntry (first %) (second %)) keyvals))))

(def example-map (my-hash-map {1 2 3 4}))
(def top-mpa {1 2, 3 4})

(defn first-test []
  (println "keys" (map #(.getKey %) (seq example-map)))
  (println "keys" (keys top-mpa))
  (println "empty" (empty example-map))
  (println "empty" (empty top-mpa))
  (println "count" (count example-map))
  (println "count" (count top-mpa))
  (println "get" (get example-map 1))
  (println "get" (get top-mpa 1))
  (println "get" (get example-map 5))
  (println "get" (get top-mpa 5))
  ;(println "dis" (dissoc top-mpa :1))
  ;(println "dis" (.without example-map :1))
  (println "cont" (contains? top-mpa 1))
  (println "cont" (contains? example-map 1)))