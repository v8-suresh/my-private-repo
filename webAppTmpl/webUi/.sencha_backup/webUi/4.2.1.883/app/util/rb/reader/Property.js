Ext.define('webUi.util.rb.reader.Property', {
    extend: 'Ext.data.reader.Json',
    alias : 'reader.property',
        
        constructor: function(config){
                config = config || {};
                
                Ext.applyIf(config, {
                successProperty: 'success',
                totalProperty: 'total'
            });
        
                this.callParent([config]);
        },
        
        getResponseData: function(response){
        	console.log(response);
                return response;
        },
        
        getData: function(data){
        		console.log('In Reader');
                var records = [], record, kv,
                        f = this.readLines(data),
                        l = f.length;
                
                for(var i = 0; i < l; i++){
                        var kl = f[i].search(/[\s:=]/);
                                record = {
                                    value : this.clearValueExtraChars(f[i].substring(kl+1)),
                                    key  :  this.clearKeyExtraChars(f[i].substring(0, kl))
                                };
                                records[i] = record;
                }
                return records;
        },
        
        clearKeyExtraChars: function(s){
                return (s ? s.replace(/[:=]/gi, "") : "");
        },
        
        clearValueExtraChars: function(s){
                return (s ? s.replace(/\\\s*\n/gi, "") : "");
        },
        readLines: function(data){
                var file = data.responseText;
                return (file ? file.match(/.*(.*\\\s*\n)+.*|^((?!^\s*[#!]).).*$/gim) : []);
        }
        

});