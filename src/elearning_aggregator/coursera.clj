(ns elearning-aggregator.coursera
  (:require [cheshire.core :refer :all]
            [clojure.string :as str]
            [elearning-aggregator.common :refer :all]))


(def coursera-url "https://api.coursera.org/api/catalog.v1/")

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


(defn get-coursera-mapping [type]
  (cond
    (= type "courses") {:id :courseraCourseId}
    (= type "categories") {:id :courseraCategoryId}
    (= type "universities") {:id :courseraUniversityId}
    (= type "instructors") {:id :courseraInstructorId}
    (= type "sessions") {:id :courseraSessionId, :courseId :courseraCourseId}
    :else (throw (IllegalArgumentException. (str "invalid type: " type)))))

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




;(def category-data (get-data "categories"))
;(def universities (for [ u universities] (dissoc u :links)))
;(def universities (for [ u universities] (clojure.set/rename-keys u (get-mapping "universities"))))
