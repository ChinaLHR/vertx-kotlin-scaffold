package io.github.lhr.core.conf


/**
 * @author lhr
 * @date 2019/4/20
 */
object HttpConf {
    /**
     * port : {"cms":10020,"api":10010}
     */
    var port: PortEntity = PortEntity

    object PortEntity {
        /**
         * cms : 10020
         * api : 10010
         */
        var cms: Int = 0
        var api: Int = 0
    }
}