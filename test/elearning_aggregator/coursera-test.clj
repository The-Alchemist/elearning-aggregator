(ns elearning-aggregator.coursera-test
  (:require [clojure.test :refer :all]
            [elearning-aggregator.coursera :refer :all]
            [elearning-aggregator.common :refer :all]))

(deftest make-url-test
  (testing "make-url with a regular String"
    (def expected "http://example.com/type?fields=a,b")
    (is (= (make-url "http://example.com/" "type" ["a", "b"]) expected))))

(deftest make-url-without-fields-test
  (testing "make-url with a regular String and no fields"
    (def expected "http://example.com/type")
    (is (= (make-url "http://example.com/" "type") expected))))

(deftest get-coursera-mapping-categories-test
  (testing "get-coursera-mapping with categories"
    (is (= (get-coursera-mapping "categories") {:id :courseraCategoryId}))))

(deftest get-coursera-mapping-instructors-test
  (testing "get-coursera-mapping with instructors"
    (is (= (get-coursera-mapping "instructors") {:id :courseraInstructorId}))))

(deftest get-coursera-mapping-invalid-test
  (testing "get-coursera-mapping with invalid type"
    (is (thrown? IllegalArgumentException (get-coursera-mapping "invalid type")))))

(deftest get-field-listing-categories
  (testing "get-field-listing for categories"
    (is (= ["description"] (get-field-listing "categories")))))

(deftest get-field-listing-invalid-type
  (testing "get-field-listing for invalid type"
    (is (thrown? IllegalArgumentException (get-field-listing "invalid")))))