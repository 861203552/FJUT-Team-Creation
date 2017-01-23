'use strict'
var gulp = require('gulp');
var less = require('gulp-less');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var browserSync = require('browser-sync');
gulp.task('style',function() {
    gulp.src('css/*.less')
        .pipe(less())
        .pipe(gulp.dest('dist/css'))
        .pipe(browserSync.reload({
            stream: true
        }));
});
gulp.task('script',function() {
    gulp.src('js/*.js')
        .pipe(concat('main.js'))
        .pipe(gulp.dest('dist/js'))
        .pipe(browserSync.reload({
            stream: true
        }));
});
gulp.task('html',function() {
    gulp.src('*.html')
        .pipe(browserSync.reload({
            stream: true
        }))
})
gulp.task('serve',function() {
    browserSync({
        server: {
            baseDir: ['./']
        }
    },function(err,bs) {
        console.log(bs.options.getIn(["urls", "local"]));
    });

    gulp.watch('css/*.less',['style']);
    gulp.watch('js/*.js',['script']);
    gulp.watch('*.html',['html']);
})
