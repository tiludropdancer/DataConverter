# DataConverter

Tool that takes data from heterogeneous sources and converts it to a canonical format.

## How to Build

```
./gradlew clean build
```

## How to Run and Test Output

```
./gradlew run -PappArgs="[${fileName}]"
```
where fileName can be 'input1.tsv', 'input2.csv', 'input3.tsv', or 'input4.csv'

```
diff output.csv reference-output.csv
```
there shouldn't be any differences
