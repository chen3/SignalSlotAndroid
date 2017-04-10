# SignalSlotAndroid
Porting [SignalSlot](https://github.com/chen3/SignalSlot) to Android

# Usage

On project build.gradle, add maven repository
```
repositories {
    jcenter()
    maven{url "https://raw.githubusercontent.com/chen3/SignalSlotAndroid/mvn-repo/"}
}
```
On moudle build.gradle, add depend
```
compile 'cn.qiditu:signalslot:1.0.1'
```
