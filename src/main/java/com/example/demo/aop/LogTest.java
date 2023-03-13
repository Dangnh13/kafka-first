package com.example.demo.aop;


import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Slf4j
public class LogTest {
    public static void main(String[] args) {
        log.trace("Trace Message!");
        log.debug("Debug Message!");
        log.info("Info Message!");
        log.warn("Warn Message!");
        log.error("Error Message!");

        // 1) trace -> trace, debug, info, warn, error
        // 2) debug ->        debug, info, warn, error
        // 3) info ->                info, warn, error
        // 4) warn ->                      warn, error
        // 5) error ->                           error
        // => trace > debug > info > warn > error
    }
}
