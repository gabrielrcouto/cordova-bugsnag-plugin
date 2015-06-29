'use strict';

var exec = require('cordova/exec'),
  cordovaBugsnag = {
  };

cordovaBugsnag.startBugsnagWithApiKey = function(apiKey, onSuccess, onFail) {
  exec(onSuccess, onFail, 'Bugsnag', 'start_bugsnag_with_api_key', [apiKey]);
};

cordovaBugsnag.notify = function(exceptionName, onSuccess, onFail) {
  exec(onSuccess, onFail, 'Bugsnag', 'notify', [exceptionName]);
};

module.exports = cordovaBugsnag;