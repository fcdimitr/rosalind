# ####################################################################
# 
#			   Docker Makefile
# 
# Author: Dimitris Floros <fcdimitr@auth.gr>
# 
# ####################################################################

SHELL:= /bin/bash

# %%%%%%%%%%%%%%%%%%%%%%%%%%%%%% COMPILATION

GRAILSRUN   = grails run-app
GRAILSWAR   = grails war
GRAILSCLEAN = grails clean
GRAILSUPMJR = grails version-update +.x.x
GRAILSUPMIN = grails version-update x.+.x
GRAILSUPSML = grails version-update x.x.+

PROJECT     = rosalind

DOCKERBUILD = docker build
DOCKERTAG   = docker tag
COMPOSEUP   = docker-compose -p $(PROJECT) up
COMPOSESTOP = docker-compose -p $(PROJECT) down
COMPOSERM   = docker-compose -p $(PROJECT) rm -f

# %%%%%%%%%%%%%%%%%%%%%%%%%%%%%% TARGETS

NAME   = rosalind
TAG    = `ls -t war-dir/rosalind-*.war | head -n1 | grep -o '[0-9].[0-9].[0-9]'`
IMG    = $(NAME):$(TAG)
LATEST = $(NAME):latest

GRAILSDIR = ..
BUILDIR   = $(GRAILSDIR)/build/libs
DOCKERDIR = .
WARDIR    = $(DOCKERDIR)/war-dir

# %%%%%%%%%%%%%%%%%%%%%%%%%%%%%% SOURCES / DIRECTORIES


# %%%%%%%%%%%%%%%%%%%%%%%%%%%%%% DEFINE TARGET RULES


# default "make" target
.DEFAULT_GOAL = help
all: $(.DEFAULT_GOAL)

# -------------------- Grails

grails-run:			## run current version
	pushd $(GRAILSDIR)/; $(GRAILSRUN); popd

grails-war:			## build WAR for current version
	pushd $(GRAILSDIR)/; $(GRAILSWAR); popd
	mv $(BUILDIR)/*.war $(WARDIR)

grails-version-major:		## increase major code version +.x.x
	pushd $(GRAILSDIR)/; $(GRAILSUPMJR); popd

grails-version-minor:		## increase minor code version x.+.x
	pushd $(GRAILSDIR)/; $(GRAILSUPMIN); popd

grails-version-small:		## increase small code version x.x.+
	pushd $(GRAILSDIR)/; $(GRAILSUPSML); popd

grails-clean:			## clean-up grails build dir
	pushd $(GRAILSDIR)/; $(GRAILSCLEAN); popd


# -------------------- Docker

disp-version:			## show current version
	pushd $(DOCKERDIR)/; echo $(TAG); popd

build-image: 			## build docker image (specified tag)
	pushd $(DOCKERDIR)/; $(DOCKERBUILD) --build-arg tag=$(TAG) -t $(IMG) .; popd
	pushd $(DOCKERDIR)/; $(DOCKERTAG) $(IMG) $(LATEST); popd

compose-deamon:			## run compose deamon
	pushd $(DOCKERDIR)/; $(COMPOSEUP) --remove-orphans -d; popd

compose-stop:			## stop compose deamon
	pushd $(DOCKERDIR)/; $(COMPOSESTOP); popd

compose-rm:			## remove compose data
	pushd $(DOCKERDIR)/; $(COMPOSERM); popd


# ==================== Miscellaneous


help:				## Print help for Makefile list
	@grep -E '(^[a-zA-Z_-]+:.*?##.*$$)|(^# --------------------)' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}{printf "\033[32m %-35s\033[0m %s\n", $$1, $$2}' | sed -e 's/\[32m # --------------------/[33m===============/'

