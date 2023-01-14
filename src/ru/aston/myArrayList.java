package ru.aston;

import java.util.Arrays;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class myArrayList<E>{
        private E[] localArray;
        private int size;

        /**
         * Пустой конструктор с заданным размером по дефолту
         */
        public myArrayList() {
            int DEFAULT_CAPACITY = 16;
            this.localArray = (E[]) new Object[DEFAULT_CAPACITY];
        }

        /**
         * Конструктор с длинной массива заданной пользователем
         *
         * @param capacity заданная длинна массива пользователем
         */
        public myArrayList(int capacity) {
            if (capacity > 0) this.localArray = (E[]) new Object[capacity];
        }

        /**
         * Добавление объекта с динамическим увеличением длинны массива
         * @param e объект пользователя
         */

        public void add(E e) {
            if (this.size == this.localArray.length) {
                this.changeSize(++size);
                add(e, this.localArray, size - 1);
            }
        }

        /**
         * Добавление объекта по индексу с динамическим увеличением длинны массива
         * @param index индекс в массиве
         * @param e объект пользователя
         */

        public void add(int index, E e) {
            Object[] temp = this.localArray;
            changeSize(++size);
            System.arraycopy(temp, index - 1, this.localArray, index, size - index);
            localArray[index] = e;
        }

        /**
         * Добавление объекта в уже увеличенный по длине массив
         *
         * @param e          Объект пользователя
         * @param localArray Текущий массив
         * @param index      индекс в массиве
         */
        private void add(E e, E[] localArray, int index) {
            localArray[index] = e;
        }

        /**
         * Получение объекта по индексу
         * @param index индекс элемента
         * @return объект в массиве
         */

        public E get(int index) {
            return this.localArray[index];
        }

        /**
         * Проверка на наличие объекта
         * @param e  объект
         * @return True - в случае совпадения, False - в случае отсутствия
         */

        public boolean contains(E e) {
            for (E value : localArray) {
                if (e.equals(value)) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        }


        /**
         * Удаление из массива по индексу
         *
         * @param index индекс элемента
         */

        public void remove(int index) {
            E[] temp = this.localArray;
            changeSize(--size);
            System.arraycopy(temp, 0, localArray, 0, size);
            System.arraycopy(temp, index + 1, localArray, index, size - index);
        }

        /**
         * Удаление объекта в массиве, если он там находится
         * @param e объект
         */

        public void remove(E e) {
            for (int i = 0; i < localArray.length; i++) {
                if (e.equals(localArray[i])) {
                    remove(i);
                }
            }
        }

        /**
         * Длинна массива
         * @return длинна массива
         */

        public int size() {
            return size;
        }

        /**
         * Сортировка массива по компаратору
         *
         * @param c Comparator<E> объекта сравнения
         */

        public void sort(Comparator<E> c) {
            while(isSorted(c)) {
                for (int k = size; k != 0; k--) {
                    for (int i = 0, j = 1; j < size; i++, j++) {
                        if (c.compare(localArray[i], localArray[j]) > 0) {
                            swap(i, j);
                        }
                    }
                }
            }
        }

        public boolean isSorted(Comparator<E> c) {
            if (this.localArray == null || this.localArray.length - 1 <= 1) {
                return Boolean.TRUE;
            }
            for (int i = 0; i < this.localArray.length; i++) {
                if (c.compare(this.localArray[i], this.localArray[i + 1]) > 0) {
                    return Boolean.FALSE;
                }
            }
            return Boolean.TRUE;
        }


        /**
         * Замена двух значений по индексу
         * @param i индекс 1 элемента
         * @param j индекс 2 элемента
         */
        private void swap(int i, int j) {
            E temp = localArray[i];
            localArray[i] = localArray[j];
            localArray[j] = temp;
        }

        /**
         * Динамическое изменение длинны массива
         *
         * @param newSize новая длинна массива
         */
        private void changeSize(int newSize) {
            this.localArray = Arrays.copyOf(this.localArray, newSize);
        }
        @Override
        public String toString() {
            return Arrays.toString(this.localArray);
        }

    }


