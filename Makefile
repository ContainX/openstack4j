# Make file used to build and test within Docker for prestine environment (Maven) tests
# Creates a Docker image locally in the format of "openstack4j-dev" with a tag of the current branch when make is executed

.PHONY: all test

GIT_BRANCH := $(shell git rev-parse --abbrev-ref HEAD 2>/dev/null)
IMAGE_NAME := openstack4j-dev$(if $(GIT_BRANCH),:$(GIT_BRANCH))
DOCKER_RUN := docker run --rm -it --privileged -e MAVEN_CONFIG=/home/builder/.m2 $(IMAGE_NAME)

default: build

all: compile test

compile: build
	$(DOCKER_RUN) mvn clean compile

test: build
	$(DOCKER_RUN) mvn verify

package: build
	$(DOCKER_RUN) mvn clean package

build:
	docker build -t "$(IMAGE_NAME)" .
