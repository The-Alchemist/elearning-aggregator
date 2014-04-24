(ns elearning-aggregator.ocw
  (:require [cheshire.core :refer :all]
            [clojure.string :as str]
            [clj-http.client :as client]))

(def url "http://data.ocwconsortium.org/api/v1/")

(defn get-data [type]
    (client/get (make-url url type) {:as :json}) )

(defn make-url
  "Creates a URL by joining the fields with a comma and adding that to a URL"
  [url type]
	    (str url type))


