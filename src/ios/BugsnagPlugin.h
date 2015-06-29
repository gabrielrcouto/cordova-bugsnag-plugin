#import <Foundation/Foundation.h>
#import <Cordova/CDV.h>
#import <Bugsnag/Bugsnag.h>


@interface BugsnagPlugin : CDVPlugin
{
// empty
}


// BUGSNAG API

-(void)start_bugsnag_with_api_key:(CDVInvokedUrlCommand*)command;
-(void)notify:(CDVInvokedUrlCommand*)command;

@end

