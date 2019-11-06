root_dir=$(pwd)
echo now working at: $root_dir

bin=$root_dir/bin
src=$root_dir/src
out=$root_dir/out
echo bin: $bin
echo src: $src
echo out: $out

touch $root_dir/srclist
cd $src
find -name "*.java" > $root_dir/srclist
cat $root_dir/srclist

mkdir $out/classes
javac -d $out/classes -encoding utf-8 -g -sourcepath $src @srclist

cd $out/classes
mkdir $out/jar
jar -cvfm $out/jar/java-socket-server.jar $root_dir/MANIFEST.MF .

sudo chmod 755 $out/jar/java-socket-server.jar
exit 0
