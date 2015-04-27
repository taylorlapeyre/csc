# Lapeyre, Taylor
# Project: PA-4
# File: bash_resize.sh
# Instructor: Feng Chen
# Class: cs4103-sp15
# LogonID: cs410319

INPUT_DIR=$1
OUTPUT_DIR=$2
RATIOS=$3

print_help() {
  echo "usage: ./batch_resize [input_dir] [output_dir] \"[resize_ratios..]\""
}

make_output_dir() {
  mkdir -p $OUTPUT_DIR
}

mirror_input_dir() {
  for dir in $INPUT_DIR/*/; do
    mkdir $OUTPUT_DIR/$(basename $dir)
  done
}

resize_image() {
  image=$1
  output_file=$2
  file_extenstion="${output_file##*.}"
  file_name="${output_file%.*}"
  size_of_original=$(wc -c $image | awk '{print $1;}')

  echo "$image size: $size_of_original"

  for ratio in $RATIOS; do
    output_image="$file_name-d$ratio.$file_extenstion"

    convert -resize "$ratio%" $image $output_image

    size_of_resized=$(wc -c $output_image | awk '{print $1;}')
    echo "$output_image size: $size_of_resized"
  done
}

get_output_dir_of_image() {
  echo $OUTPUT_DIR/$(basename $(basename $(dirname $1)))
}

resize_all_images() {
  for dir in $INPUT_DIR/*; do
    for image in $dir/*; do
      output_dir=$(get_output_dir_of_image $image)/$(basename $image)
      resize_image $image $output_dir
    done
  done
}

print_recap() {
  originals=$(find $INPUT_DIR -type f | wc -l)
  resized=$(find $OUTPUT_DIR -type f | wc -l)
  size_of_originals=$(du -hs $INPUT_DIR)
  size_of_resized=$(du -hs $OUTPUT_DIR)
  echo "Number of original images: $originals"
  echo "Final number of images: $resized"
  echo "Total size of original images: $size_of_originals"
  echo "Total size of resized images: $size_of_resized"
}


if [ "$1" == "help" ]; then
  print_help
  exit
fi

if [ -d $OUTPUT_DIR ]; then
  echo "error: output directory $1 already exists"
  exit
fi

make_output_dir
mirror_input_dir
resize_all_images
print_recap
