#!/bin/python

# Turn on the light for your loved one... I'm such a fruit cake. :p
# 
# The binary must first be installed so that the person's name and locations
# are configured in this  
#
# Author: buttons

import os
import sys
import urllib2

def main(argv):
	agentMap = MAP
	impPrefix = "https://agent.electricimp.com/"

	if (len(argv) < 2):
		print "Usage: ./hi-love location [pulse time]"
		return

	# Parse the cmd line arguments
	location = argv[1]
	time = 500 # ms
	if (len(argv) == 3): 
		time = int(argv[3]) * 100 # ms

	# Check to make sure the agent location exists
	if (location in agentMap):
		url = impPrefix + agentMap[location] + "?time=" + str(time)
		response = urllib2.urlopen(url).read()
		print "Result: " + str(response)
		print "Done."
	else:
		print "Location not found. Exiting now."
		return

if __name__ == "__main__":
	main(sys.argv)