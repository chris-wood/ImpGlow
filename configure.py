#!/bin/python

# Configure the hi-love program.
#
# Author: buttons

import os

PROGRAMNAME = "hi-love"

def main():
	performInstall = False
	if os.path.isfile(PROGRAMNAME):
		answer = raw_input("The file " + PROGRAMNAME + " already exists -- continue? (y/n): ")
		if "n" in answer:
			pass
		else:
			os.remove(PROGRAMNAME)
			performInstall = True
	if performInstall:
		install()
		return
	else:
		print "Quitting..."
		return

def install():
	print "\n"
	print "==== Welcome ====="
	print "\n"
	name = raw_input("Enter the name of your loved one: ")
	print "\n"
	print "=================="
	print "Enter a CSV list of location:id tuples"
	print "e.g., work:1234,home:5678"
	print "\n"
	tuples = raw_input("")

	tupleList = tuples.split(",")
	agentMap = {}
	for tup in tupleList:
		data = tup.split(":")
		location = data[0].strip()
		impId = data[1].strip()
		if location in agentMap:
			print "Error: Location " + location + " was already specified. Exiting."
			return
		agentMap[location] = impId

	print(agentMap)

	template = open(PROGRAMNAME + ".py", "r")
	executable = open(PROGRAMNAME, "w")
	for line in template:
		line = line.strip()
		if "agentMap = MAP" in line:
			executable.write("agentMap = " + str(agentMap) + "\n")
		elif "NAME" in line:
			executable.write(line.replace("NAME", name) + "\n")
		else:
			executable.write(line + "\n")
	executable.flush()
	executable.close()
	os.chmod(PROGRAMNAME, 777) 

if __name__ == "__main__":
	main()