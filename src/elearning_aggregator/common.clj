(ns elearning-aggregator.common)


(defn map-without-empty-values [m]
  (into {}
    ; note: v could be a string or int (e.g., "abc" or 123), so empty? by itself doesn't work
    (for [ [k v] m :when (not= v "") ] [ k v ] )))

(defn clean-data [data type mapping-fn]
  (def data1 (for [ u data ] (dissoc u :links)))
  (def data2 (for [ u data1 ] (clojure.set/rename-keys u (mapping-fn type))))
  (def data3 (for [ u data2 ] (map-without-empty-values u)))
  data3)



