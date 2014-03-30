(ns elearning-aggregator.coursera-test
  (:require [clojure.test :refer :all]
            [elearning-aggregator.coursera :refer :all]))

(deftest make-url-test
  (testing "make-url with a regular String"
    (def expected "http://example.com/type?fields=a,b")
    (is (= (make-url "http://example.com/" "type" ["a", "b"]) expected))))

(deftest make-url-without-fields-test
  (testing "make-url with a regular String and no fields"
    (def expected "http://example.com/type")
    (is (= (make-url "http://example.com/" "type") expected))))

(deftest get-mapping-categories-test
  (testing "get-mapping with categories"
    (is (= (get-mapping "categories") {:id :courseraCategoryId}))))

(deftest get-mapping-instructors-test
  (testing "get-mapping with instructors"
    (is (= (get-mapping "instructors") {:id :courseraInstructorId}))))

(deftest get-mapping-invalid-test
  (testing "get-mapping with invalid type"
    (is (thrown? IllegalArgumentException (get-mapping "invalid type")))))

(deftest map-without-empty-values-with-real-data-test
  (testing "map-without-empty-values with pseudo real data"
    (def expected {:lastName "Kleinsteuber", :courseraInstructorId 2693792, :bio "bio", :firstName "Martin", :photo "https://coursera-instructor-photos.s3.amazonaws.com/61/5bd80181955cc186a5ec15f858c8e6/image001.jpg", :title "Professor", :department "Electrical Engineering and Information Technology", :website "http://www.gol.ei.tum.de/index.php?id=14&L=1", :photo150 "https://coursera-instructor-photos.s3.amazonaws.com/22/e04cf6c6e338ddf76f5e4c66c20119/image001.jpg"})
    (is (= expected (map-without-empty-values {:lastName "Kleinsteuber", :middleName "", :courseraInstructorId 2693792, :bio "bio", :firstName "Martin", :prefixName "", :websiteTwitter "", :photo "https://coursera-instructor-photos.s3.amazonaws.com/61/5bd80181955cc186a5ec15f858c8e6/image001.jpg", :title "Professor", :websiteFacebook "", :fullName "", :websiteLinkedin "", :department "Electrical Engineering and Information Technology", :websiteGplus "", :suffixName "", :website "http://www.gol.ei.tum.de/index.php?id=14&L=1", :photo150 "https://coursera-instructor-photos.s3.amazonaws.com/22/e04cf6c6e338ddf76f5e4c66c20119/image001.jpg"})))))

(deftest get-field-listing-categories
  (testing "get-field-listing for categories"
    (is (= ["description"] (get-field-listing "categories")))))

(deftest get-field-listing-invalid-type
  (testing "get-field-listing for invalid type"
    (is (thrown? IllegalArgumentException (get-field-listing "invalid")))))