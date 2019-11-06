root_dir=$(pwd)
echo $root_dir

function build() {
    bin=$root_dir/bin
    src=$root_dir/src
    out=$root_dir/out
    echo bin
    echo src
    echo out

    touch $root_dir/srclist
    find $src -name "*.java" > $root_dir/srclist
    cat $root_dir/srclist

    javac -d $out/classes -encoding utf-8 -g -sourcepath $src @srclist

    cd $out/claases
    mkdir $out/jar
    jar -cvfm $out/jar/java-socket-server.jar *

    sudo chmod a+x $out/jar/java-socket-server.jar
}

build
exit 0
