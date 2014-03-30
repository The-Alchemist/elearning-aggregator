(ns elearning-aggregator.load
  (:require [cheshire.core :refer :all]
            [clojure.string :as str]
            [korma.db :refer :all]
            [korma.core :refer :all]
            [clojure.java.io :as io]
            [clojurewerkz.propertied.properties :as p]
            [elearning-aggregator.coursera :refer :all]))

(def db-props (p/load-from (io/resource "db.properties")))
(def db-props-keywords (into {} 
  (for [[k v] db-props] [(keyword k) v])))
(def mys (mysql db-props-keywords))
(defdb rds mys)
; Category
(declare Category)
(defentity Category)
;(exec (select* Category))
; Institution
(declare Institution)
(defentity Institution)
; Instructor
(declare Instructor)
(defentity Instructor)

