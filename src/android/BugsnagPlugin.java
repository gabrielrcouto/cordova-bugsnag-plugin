package br.com.memed.bugsnag;

import android.content.Context;
import android.text.TextUtils;
import com.bugsnag.android.Bugsnag;

import java.util.Map;
import java.util.HashMap;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BugsnagPlugin extends CordovaPlugin {

    private static String LOG_TAG = "BUGSNAG PLUGIN";

    private enum Action {

        START_BUGSNAG_WITH_API_KEY("start_bugsnag_with_api_key"),
        NOTIFY("notify");

        private final String name;
        private static final Map<String, Action> lookup = new HashMap<String, Action>();

        static {
            for (Action a : Action.values()) lookup.put(a.getName(), a);
        }

        private Action(String name) { this.name = name; }
        public String getName() { return name; }
        public static Action get(String name) { return lookup.get(name); }
    }

    /**
     * helper fn that logs the err and then calls the err callback
     */
    private void error(CallbackContext cbCtx, String message) {
        LOG.e(LOG_TAG, message);
        cbCtx.error(message);
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext cbCtx) {
        // throws JSONException
        Action act = Action.get(action);

        if (act == null){
            this.error(cbCtx, "unknown action");
            return false;
        }

        switch (act) {
            case START_BUGSNAG_WITH_API_KEY:
                return handleStartBugsnagWithApiKey(args, cbCtx);
            case NOTIFY:
                return handleNotify(args, cbCtx);
            default:
                this.error(cbCtx, "unknown action");
                return false;
        }
    }

    //************************************************
    //  ACTION HANDLERS
    //   - return true:
    //     - to indicate action was executed with correct arguments
    //     - also if the action from sdk has failed.
    //  - return false:
    //     - arguments were wrong
    //************************************************

    private boolean handleStartBugsnagWithApiKey(JSONArray args, final CallbackContext cbCtx) {
        Context ctx = cordova.getActivity();
        
        Bugsnag.init(ctx);

        cbCtx.success();
        return true;
    }

    private boolean handleNotify(JSONArray args, final CallbackContext cbCtx) {
        String exceptionName = args.optString(0, "");

        if (TextUtils.isEmpty(exceptionName)) {
            this.error(cbCtx, "missing exception name");
            return false;
        }

        Context ctx = cordova.getActivity();
        
        Bugsnag.notify(new Exception(exceptionName));

        cbCtx.success();
        return true;
    }
}