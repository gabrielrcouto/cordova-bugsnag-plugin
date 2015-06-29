#import "BugsnagPlugin.h"

@implementation BugsnagPlugin

// BUGSNAG API

-(void)start_bugsnag_with_api_key:(CDVInvokedUrlCommand*)command;
{
    CDVPluginResult* pluginResult = nil;
    NSArray* arguments = command.arguments;
    NSString* apiKey = [arguments objectAtIndex:0];

    if (apiKey == nil || 0 == [apiKey length])
    {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"API Key is missing"];
    }
    else
    {
        [Bugsnag startBugsnagWithApiKey:apiKey];
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

-(void)notify:(CDVInvokedUrlCommand*)command;
{
    CDVPluginResult* pluginResult = nil;
    NSArray* arguments = command.arguments;
    NSString* exceptionName = [arguments objectAtIndex:0];

    if (exceptionName == nil || 0 == [exceptionName length])
    {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Exception Name is missing"];
    }
    else
    {
        [Bugsnag notify:[NSException exceptionWithName:exceptionName reason:nil userInfo:nil]];
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
