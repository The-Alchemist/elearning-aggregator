(ns elearning-aggregator.common-test
  (:require [clojure.test :refer :all]
            [elearning-aggregator.common :refer :all]))

(deftest map-without-empty-values-with-real-data-test
  (testing "map-without-empty-values with pseudo real data"
    (def expected {:lastName "Kleinsteuber", :courseraInstructorId 2693792, :bio "bio", :firstName "Martin", :photo "https://coursera-instructor-photos.s3.amazonaws.com/61/5bd80181955cc186a5ec15f858c8e6/image001.jpg", :title "Professor", :department "Electrical Engineering and Information Technology", :website "http://www.gol.ei.tum.de/index.php?id=14&L=1", :photo150 "https://coursera-instructor-photos.s3.amazonaws.com/22/e04cf6c6e338ddf76f5e4c66c20119/image001.jpg"})
    (is (= expected (map-without-empty-values {:lastName "Kleinsteuber", :middleName "", :courseraInstructorId 2693792, :bio "bio", :firstName "Martin", :prefixName "", :websiteTwitter "", :photo "https://coursera-instructor-photos.s3.amazonaws.com/61/5bd80181955cc186a5ec15f858c8e6/image001.jpg", :title "Professor", :websiteFacebook "", :fullName "", :websiteLinkedin "", :department "Electrical Engineering and Information Technology", :websiteGplus "", :suffixName "", :website "http://www.gol.ei.tum.de/index.php?id=14&L=1", :photo150 "https://coursera-instructor-photos.s3.amazonaws.com/22/e04cf6c6e338ddf76f5e4c66c20119/image001.jpg"})))))
