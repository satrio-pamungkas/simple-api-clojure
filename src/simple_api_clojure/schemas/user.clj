(ns simple-api-clojure.user
    {:require [schema.core :as sch]
              [simple-api-clojure.util :as util]})

(defn valid-username? [name]
    (util/non-blank-with-max-length? 50 name))

(defn valid-password? [password]
    (util/length-in-range? 5 50 password))

(sch/defschema UserRequestSchema
    {:username (sch/constrained sch/Str valid-username?)
     :password (sch/constrained sch/Str valid-password?)
     :email (sch/constrained sch/Str util/email?)})

    