(ns elearning-aggregator.coursera-test
  (:require [clojure.test :refer :all]
            [elearning-aggregator.coursera :refer :all]))

(deftest make-url-test
  (testing "make-url with a regular String"
    (def expected "http://example.com/?fields=a,b")
    (is (= (make-url "http://example.com/" ["a", "b"]) expected))))

(deftest make-url-without-fields-test
  (testing "make-url with a regular String and no fields"
    (def expected "http://example.com/")
    (is (= (make-url "http://example.com/" []) expected))))

(deftest get-mapping-categories-test
  (testing "get-mapping with categories"
    (is (= (get-mapping "categories") {:id :courseraCategoryId}))))

(deftest get-mapping-instructors-test
  (testing "get-mapping with instructors"
    (is (= (get-mapping "instructors") {:id :courseraCategoryId}))))

(deftest get-mapping-invalid-test
  (testing "get-mapping with invalid type"
    (is (thrown? IllegalArgumentException (get-mapping "invalid type")))))

(deftest get-field-listing-categories
  (testing "get-field-listing for categories"
    (is (= ["description"] (get-field-listing "categories")))))

(deftest get-field-listing-invalid-type
  (testing "get-field-listing for invalid type"
    (is (thrown? IllegalArgumentException (get-field-listing "invalid")))))