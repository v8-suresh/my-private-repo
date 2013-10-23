/**
 * This is the Logo Widget placed in Footer
 */
 Ext.define('webUi.view.common.FooterLogo', {
	    extend: 'Ext.panel.Panel',
	    xtype: 'd-footer-logo',
	    bodyPadding : '2 2 2 2',
//	    margin: 2,
	    border: false,
	    layout: {
	        type: 'vbox',
	        align: 'right'
	    },
	    items: [
	        {
	        	html: '<img src="img/footer-logo.png">',
	        	border: false
	        }
	    ]
	});