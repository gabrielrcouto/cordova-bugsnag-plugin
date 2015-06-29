'use strict';

var exec = require('cordova/exec'),
  cordovaBugsnag = {
  };

cordovaBugsnag.startBugsnagWithApiKey = function(apiKey) {
  exec('Bugsnag', 'start_bugsnag_with_api_key', [apiKey]);
};

cordovaBugsnag.notify = function(exceptionName) {
  exec('Bugsnag', 'notify', [exceptionName]);
};

module.exports = cordovaBugsnag;