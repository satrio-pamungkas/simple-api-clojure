(ns simple-api-clojure.schemas.user
    (:require [schema.core :as s]
              [simple-api-clojure.util :as util]))

(defn valid-username? [name]
    (util/non-blank-with-max-length? 50 name))

(defn valid-password? [password]
    (util/length-in-range? 5 50 password))

(s/defschema UserRequestSchema
    {:username (s/constrained s/Str valid-username?)
     :password (s/constrained s/Str valid-password?)
     :email (s/constrained s/Str util/email?)})

    