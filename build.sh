root_dir=$(pwd)
echo $root_dir

bin=$root_dir/bin
src=$root_dir/src
out=$root_dir/out
echo $bin
echo $src
echo $out

touch $root_dir/srclist
find $src -name "*.java" > $root_dir/srclist
cat $root_dir/srclist

mkdir $out/classes
javac -d $out/classes -encoding utf-8 -g -sourcepath $src @srclist

cd $out/classes
mkdir $out/jar
jar -cvfm $out/jar/java-socket-server.jar *

sudo chmod 755 $out/jar/java-socket-server.jar
exit 0
