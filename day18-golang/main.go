package main

import (
	"flag"
	"fmt"
	"log"
	"math/rand"
	"net/http"
	"os"
	"strconv"
	"time"

	"github.com/gin-gonic/gin"
)

const (
	DIR_PUBLIC        = "public"
	DIR_TEMPLATES     = "templates"
	ENV_INSTANCE_NAME = "INSTANCE_NAME"
	ENV_INSTANCE_HASH = "INSTANCE_HASH"
	ENV_PORT          = "PORT"
)

type CliOpt struct {
	port int
	name string
	hash string
}

func randNums(count int) []int {

	var num [14]int

	rand.Seed(time.Now().UnixNano())

	for i := 0; i < 14; i++ {
		num[i] = i
	}

	for i := 0; i < len(num); i++ {
		tmp := num[i]
		p := rand.Intn(len(num))
		num[i] = num[p]
		num[p] = tmp
	}

	return num[0:count]
}

func parseCommandLine() CliOpt {

	var port int
	var name string
	var hash string

	p := os.Getenv(ENV_PORT)
	if "" == p {
		port = 3000
	} else {
		var err error
		port, err = strconv.Atoi(p)
		if nil != err {
			log.Fatalf("Invalid port number: %s\n", p)
			os.Exit(1)
		}
	}

	name = os.Getenv(ENV_INSTANCE_NAME)
	hash = os.Getenv(ENV_INSTANCE_HASH)

	flag.IntVar(&port, "port", port, "port to listen to")
	flag.StringVar(&name, "name", name, "set the instance name")
	flag.StringVar(&hash, "hash", hash, "set the instance hash")
	flag.Parse()

	return CliOpt{port, name, hash}
}

func getPath(path string) (*string, error) {
	dirname, err := os.Getwd()
	if nil != err {
		return nil, err
	}
	fullPath := fmt.Sprintf("%s/%s", dirname, path)
	if _, err = os.Stat(fullPath); os.IsNotExist(err) {
		return nil, err
	}
	return &fullPath, nil
}

func main() {

	opt := parseCommandLine()

	r := gin.Default()

	staticDir, err := getPath(DIR_PUBLIC)
	if nil != err {
		log.Fatalf("Cannot find 'public' directory: %v\n", err)
		os.Exit(1)
	}
	r.Static("/static", *staticDir)

	templateDir, err := getPath(DIR_TEMPLATES)
	if nil != err {
		log.Fatalf("Cannot find 'templates' directory: %v\n", err)
		os.Exit(1)
	}
	r.LoadHTMLGlob(fmt.Sprintf("%s/*", *templateDir))

	r.GET("/healthz", func(c *gin.Context) {
		c.JSON(204, gin.H{})
	})

	f := func(c *gin.Context) {
		num, err := strconv.Atoi(c.DefaultQuery("num", "4"))
		if nil != err {
			num = 4
		}
		dovs := randNums(num)
		c.HTML(http.StatusOK, "index.html", gin.H{
			"instanceName": opt.name,
			"instanceHash": opt.hash,
			"dovs":         dovs,
		})
	}
	r.GET("/", f)
	r.GET("/index.html", f)

	fmt.Printf("Starting application at %s on port %d\n", time.Now().UTC().String(), opt.port)

	if err := r.Run(fmt.Sprintf("0.0.0.0:%d", opt.port)); nil != err {
		log.Fatalf("Cannot start dov-bear: %v\n", err)
		os.Exit(1)
	}
}
