Prerequisits

1) define following environment variables

MTG_DB_USER=USER

MTG_DB_PWD=PASSWORD

MTG_FILE_DIR_PATH=/PATH_TO_MTG_FILES (without '/')

MTG_INSTA_APP_ID=1148266559032113

MTG_INSTA_APP_SECRET=dcd412f915d35c5bee753c58a948bae9

MTG_SERVER_PORT=8080

2) java 1.8 or jdk 15
3) maven
4) pm2

To run server:

    cd mtg
    mvn clean 
    mvn package  
    pm2 start process.json (for first time) else >> pm2 start mtg
    

To stop server:

    pm2 stop mtg

By CutupAngel.

