(ns itmo-functional-programming.lab2.hash-map
  (:import (clojure.lang IMeta ILookup Seqable IPersistentMap MapEquivalence IPersistentCollection Associative IMapEntry)))

(defrecord MyHashMapEntry [key val]
  IMapEntry
  (getKey [_] key)
  (getValue [_] val))

(defn inc-load-by [load arr amount] (+ load (/ (double amount) (count arr))))

(def compute-meta
  (memoize (fn [contents] {:size (count contents)})))

(defn exact-entry? [key candidate]
  (.equals key (:key candidate)))

(defn find-entry [arr key]
  (loop [computed (-> key hash int Math/abs)
         times-left (count arr)]
    (let [pos (rem computed (count arr))]
      (if-let [entry (nth arr pos)]
        (if (exact-entry? key entry)
          [entry pos]
          (if (> times-left 0)
            (recur (inc computed) (dec times-left)) [nil nil]))
        [nil pos]))))

(defn insert-entries
  [amount arr new-entries]
  (if (or (empty? new-entries) (<= amount 0))
    [arr new-entries]
    (if-let [pos (last (find-entry arr (:key (first new-entries))))]
      (recur (dec amount) (assoc arr pos (first new-entries)) (rest new-entries))
      (println "Reached nil: " amount arr new-entries))))

(defn insert [load arr new]
  (let [next-load (inc-load-by load arr 1)]
    [(first (insert-entries 1 arr [new])) next-load]))

(defn delete [arr key]
  (if-let [pos (last (find-entry arr key))]
    (assoc arr pos (->MyHashMapEntry key nil))
    arr))

(declare ->MyHashMap)
(deftype MyHashMap [contents load]
  IMeta
  (meta [_] (compute-meta contents))

  ILookup
  (valAt [_ k not-found]
    (if-let [[attempt _] (find-entry contents k)]
      (:val attempt)
      not-found))

  (valAt [m k] (.valAt m k nil))

  Seqable
  (seq [_] (seq contents))

  IPersistentMap
  (assoc [_ k v] (apply ->MyHashMap (insert load contents (->MyHashMapEntry k v))))
  (without [_ k] (->MyHashMap (delete contents k) load))

  MapEquivalence

  IPersistentCollection
  (count [m] (:size (.meta m)))
  (empty [_] (->MyHashMap [] 1.0))

  Associative
  (containsKey [_ k] (let [[attempt _] (find-entry contents k)]
                       (if-not nil? attempt)
                       true
                       false)))

(defn my-hash-map
  ([] (->MyHashMap [] 0.0))
  ([src-map] (my-hash-map src-map 2))
  ([src-map coefficient] (->MyHashMap (->>
                                        src-map
                                        (map #(->MyHashMapEntry (first %) (last %))))
                                      (/ 1.0 coefficient))))

(def example-map (my-hash-map {1 2 3 4 5 6 7 8}))
(def hm (hash-map 1 2 3 4 5 6 7 8))
