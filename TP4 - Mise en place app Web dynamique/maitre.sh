./create_network.sh

if [ $? !=0 ]; then
	echo "echec de lancement du script create_network"
	exit 1
fi

./run_mysql.sh

if [ $? !=0 ]; then
	echo "echec de lancement du script run_mysql.sh"
	exit 1
fi

./filldb.sh

if [ $? !=0 ]; then
        echo "echec de lancement du script filldb.sh"
        exit 1
fi

./build_image.sh

if [ $? !=0 ]; then
        echo "echec de lancement du script build_image"
        exit 1
fi

./run-app.sh

if [ $? !=0 ]; then
        echo "echec de lancement du script run-app.sh"
        exit 1
fi


