[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.nenadalm/uri.svg)](https://clojars.org/org.clojars.nenadalm/uri)
[![Build Status](https://secure.travis-ci.org/nenadalm/uri.png?branch=master)](http://travis-ci.org/nenadalm/uri)
[![License](https://img.shields.io/badge/License-EPL%201.0-red.svg)](https://opensource.org/licenses/EPL-1.0)

This is a library that makes working with URIs in Clojure and ClojureScript a
little more pleasant.

Fork of [arohner/uri](https://github.com/arohner/uri) with support for nested parames and clojurescript.

## Usage

The `cemerick.uri/uri` function returns an instance of the
`cemerick.uri.URI` record type that allows you to easily work with each
datum within the provided URI:

```clojure
=> (require '[cemerick.uri :refer (uri uri-encode)])
nil
=> (-> (uri "https://api.stripe.com/v1/charges")
     (assoc :username "vtUQeOtUnYr7PGCLQ96Ul4zqpDUO4sOE")
     str)
"https://vtUQeOtUnYr7PGCLQ96Ul4zqpDUO4sOE:@api.stripe.com/v1/charges"
```

`uri` will also accept additional paths to be resolved against the path
in the base URI:

```clojure
=> (uri "https://api.twitter.com/")
#cemerick.uri.URI{:protocol "https", :username nil, :password nil,
                  :host "api.twitter.com", :port -1, :path "/", :query nil,
                  :anchor nil}
=> (uri "https://api.twitter.com/" "1" "users" "profile_image" "cemerick")
#cemerick.uri.URI{:protocol "https", :username nil, :password nil,
                  :host "api.twitter.com", :port -1,
                  :path "/1/users/profile_image/cemerick", :query nil, :anchor nil}
=> (str *1)
"https://api.twitter.com/1/users/profile_image/cemerick"
=> (str (uri "https://api.twitter.com/1/users/profile_image/cemerick" "../../lookup.json"))
"https://api.twitter.com/1/users/lookup.json"
```

The `:query` slot can be a string or a map of params:

```clojure
=> (str (assoc *3 :query {:a 5 :b 6}))
"https://api.twitter.com/1/users/profile_image/cemerick?a=5&b=6"
```

Note that `uri` does not perform any uri-encoding of paths.  Use
`cemerick.uri/uri-encode` to uri-encode any paths/path components prior
to passing them to `uri`.  e.g.:

```clojure
=> (def download-root "http://foo.com/dl")
#'cemerick.test-uri/download-root
=> (str (uri download-root "/"))
"http://foo.com/"
=> (str (uri download-root (uri-encode "/")))
"http://foo.com/dl/%2F"
=> (str (uri download-root (uri-encode "/logical/file/path")))
"http://foo.com/dl/%2Flogical%2Ffile%2Fpath"
```

## License

Copyright ©2012 [Chas Emerick](http://cemerick.com) and other contributors

Distributed under the Eclipse Public License, the same as Clojure.
Please see the `epl-v10.html` file at the top level of this repo.
