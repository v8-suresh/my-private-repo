/**
 * Singletons used throughout the application
 */
Ext.define('webUi.util.AppSingleton', {
	singleton : true,
	

	currentBundle : '',
	lang : 'en',
	isBundleLoaded : false,
	uiRsrcUrl: '/web-1.0/appConfig/resources',
	appParamUrl: '/web-1.0/appConfig/params',
	bundle: null,
	isBundleLoaded: false,
	appParam: null,
	isAppParamLoaded: false,
    defaultLanguage : 'en',
    messages : {
        'en' : {
		    'msgKeyNotFound': 'Message not found for key:',
		    'msgBundleNotLoaded': 'Bundle Not loaded, Pls reload the application or Contact System ADMIN',
		    'msgAppParamNotLoaded': 'Application Parameters Not loaded, Pls reload the application or Contact System ADMIN',
		    'msgError': 'Error',
		    'loading': '<b>Loading Items....</b>',
		    'rsrcBndlLoadMsg': 'Loading Resource Bundle Items .......................',
		    'appConfigLoadMsg': 'Loading App Configuration Items .....................'
		},
        'es' : {
		    'msgKeyNotFound': 'Message not found for key:_ES',
		    'msgBundleNotLoaded': 'Bundle Not loaded, Pls reload the application or Contact System ADMIN_ES',
		    'msgAppParamNotLoaded': 'Application Parameters Not loaded, Pls reload the application or Contact System ADMIN_ES',
		    'msgError': 'Error_ES',
		    'loading': 'Loading Items _Es',
		    'rsrcBndlLoadMsg': 'Loading Resource Bundle Items ......................._ES',
		    'appConfigLoadMsg': 'Loading App Configuration Items ....................._ES'
        }
    },
    activeMasks: null,
	constructor : function(config){
        this.defaultLanguage = (navigator.language || navigator.browserLanguage || navigator.userLanguage || this.defaultLanguage).substring(0, 2);
        this.activeMasks = Ext.create('Ext.util.HashMap');
	},
    getMsg: function(key){
		if(this.bundle == null){
			this.handleError(this.getAppConfigMsg('msgBundleNotLoaded'));
			return '';
		}
    	var msg = this.bundle.get(key);
    	if((msg != null) && (msg !== 'undefined') && 
    			(arguments.length > 0) && (Ext.isArray(arguments[1])) && (arguments[1].length > 0)){
    		var phCnt = arguments[1].length;
    		for(ph=0; ph<phCnt; ph++){
    			msg = msg.replace(new RegExp("\\{" + ph + "\\}", "gi" ), arguments[1][ph]);
    		}
    	}
    	msg = (((msg != null) && (msg != 'undefined')) ? msg : this.getAppConfigMsg('msgKeyNotFound') + key);
    	return msg;
    },
    getAppParam: function(key){
		if(this.appParam == null){
			this.handleError(this.getAppConfigMsg('msgAppParamNotLoaded'));
			return '';
		}
    	var msg = this.appParam.get(key);
    	msg = (((msg != null) && (msg != 'undefined')) ? msg : this.getAppConfigMsg('msgKeyNotFound') + key);
    	return msg;
    },
    handleError: function(msg, logMsg){
    	console.log(msg);
    	console.log(logMsg);
//    	Ext.Msg.alert(this.getAppConfigMsg('msgError'), msg);
//    	if(logMsg){
//    		console.log(logMsg);
//    	}
    },
    checkAppConfigLoaded: function(){
    	return (this.isBundleLoaded && this.isAppParamLoaded);
    },
    getAppConfigMsg: function(key){
    	return this.messages[this.defaultLanguage][key];
    },
    
	showMask: function(maskKey, maskMsg){
		if(this.hasActiveMask()){
			this.activeMasks.add(maskKey, maskMsg);
			this.addMessageToMask(maskMsg);
		} else {
			this.activeMasks.add(maskKey, maskMsg);
			Ext.getBody().mask(maskMsg);
		}
	},
	removeMask: function(maskKey){
		if(this.activeMasks.containsKey(maskKey)){
			if(this.activeMasks.getCount() == 1){
				Ext.getBody().unmask();
			} else {
				var msg = this.activeMasks.get(maskKey);
				if(this.activeMasks.removeAtKey(maskKey)){
					this.removeMsgFromMask(msg);
				}
			}
		}
	},
	hasActiveMask: function(){
		return Ext.getBody().isMasked();
	},
	addMessageToMask: function(msg){
		this.refreshMaskMsg(msg);
	}, 
	removeMsgFromMask: function(msg){
		this.refreshMaskMsg(msg);
	},
	refreshMaskMsg: function(msg){
		var msg = this.getAppConfigMsg('loading');
		this.activeMasks.each(function(key, value, length){
			msg = msg.concat('<br>');
			msg = msg.concat(value);
		});
		Ext.getBody().mask(msg);
	}
    

});