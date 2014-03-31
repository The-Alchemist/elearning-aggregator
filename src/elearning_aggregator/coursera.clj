(ns elearning-aggregator.coursera
  (:require [cheshire.core :refer :all]
            [clojure.string :as str]))


(def coursera-url "https://api.coursera.org/api/catalog.v1/")

(defn get-mapping [type]
  (cond
    (= type "courses") {:id :courseraCourseId}
    (= type "categories") {:id :courseraCategoryId}
    (= type "universities") {:id :courseraUniversityId}
    (= type "instructors") {:id :courseraInstructorId}
    (= type "sessions") {:id :courseraSessionId, :courseId :courseraCourseId}
    :else (throw (IllegalArgumentException. (str "invalid type: " type)))))

(defn get-field-listing [type]
  (cond
    (= type "courses")
		  [
		  "language" 
			"largeIcon" 
			"photo" 
			"previewLink" 
			"shortDescription" 
			"smallIcon" 
			"smallIconHover" 
			"subtitleLanguagesCsv" 
			"isTranslate" 
			"universityLogo" 
			"universityLogoSt" 
			"video" 
			"videoId" 
			"aboutTheCourse" 
			"targetAudience"
      "faq" 
			"courseSyllabus" 
			"courseFormat" 
			"suggestedReadings" 
			"instructor" 
			"estimatedClassWorkload" 
			"aboutTheInstructor" 
			"recommendedBackground"
			]
    (= type "categories")
      ["description"]
    (= type "universities")
			["name" 
			"shortName" 
			"description" 
			"banner" 
			"homeLink" 
			"location" 
			"locationCity" 
			"locationState" 
			"locationCountry" 
			"locationLat" 
			"locationLng" 
			"classLogo" 
			"website" 
			"websiteTwitter" 
			"websiteFacebook" 
			"websiteYoutube" 
			"logo" 
			"squareLogo" 
			"landingPageBanner"]
   (= type "instructors")
     ["photo" 
			"photo150" 
			"bio" 
			"prefixName" 
			"firstName" 
			"middleName" 
			"lastName" 
			"suffixName" 
			"fullName" 
			"title" 
			"department" 
			"website" 
			"websiteTwitter" 
			"websiteFacebook" 
			"websiteLinkedin" 
			"websiteGplus" 
			"shortName"]
    (= type "sessions")
			["id" 
			"courseId" 
			"homeLink" 
			"status" 
			"active" 
			"durationString" "startDay" 
			"startMonth" 
			"startYear" 
			"name" 
			"signatureTrackCloseTime" 
			"signatureTrackOpenTime" 
			"signatureTrackPrice" 
			"signatureTrackRegularPrice" 
			"eligibleForCertificates" 
			"eligibleForSignatureTrack" 
			"certificateDescription" 
			"certificatesReady"]
    :else
      (throw (IllegalArgumentException. (str "invalid type: " type)))))

(defn make-url
  "Creates a URL by joining the fields with a comma and adding that to a URL"
  ([url type] 
    (make-url url type []))
  ([url type fields]
	  (if (empty? fields)
	    (str url type)
		  (str url type "?fields=" (clojure.string/join "," fields) ))))
  

(defn get-data
  ([type]
    (get-data type (get-field-listing type)))
  ([type fields]
    ((parse-string (slurp (make-url coursera-url type fields)) true) :elements)))

(defn map-without-empty-values [m]
  (into {}
    ; note: v could be a string or int (e.g., "abc" or 123), so empty? by itself doesn't work
    (for [ [k v] m :when (not= v "") ] [ k v ] )))

(defn clean-data [data type]
  (def data1 (for [ u data ] (dissoc u :links)))
  (def data2 (for [ u data1 ] (clojure.set/rename-keys u (get-mapping type))))
  (def data3 (for [ u data2 ] (map-without-empty-values u)))
  data3)

;(def category-data (get-data "categories"))
;(def universities (for [ u universities] (dissoc u :links)))
;(def universities (for [ u universities] (clojure.set/rename-keys u (get-mapping "universities"))))
