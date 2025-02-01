docker run \
  -d \
  --name axonserver \
  -p 8024:8024 \
  -p 8124:8124 \
  -p 8123:8123 \
  axoniq/axonserver